package net.dw101.sqlmapper

import java.sql.ResultSet
import javax.sql.DataSource

import scala.reflect.runtime.universe.typeTag
import scala.reflect.runtime.{universe => ru}


object SqlMapper {

    implicit class SqlContext(val sc: StringContext) extends AnyVal {
        def sql(args: Any*): SqlExecutor = {
            new SqlExecutor(sc.raw(args.toSeq: _*))
        }

        def mapper(args: Any*): SqlExecutor = ???
    }

    def query[E: ru.TypeTag](ds: DataSource, command: CommandDefinition): Iterator[E] = {

        val sql = command.commandText
        val pstmt = ds.getConnection.prepareStatement(sql)
        val rs = pstmt.executeQuery()
        /*
                val m = ru.runtimeMirror(getClass.getClassLoader)
                val classType = ru.typeOf[E].typeSymbol.asClass
                val constructor = typeTag[E].tpe.decl(ru.termNames.CONSTRUCTOR).asMethod
                val constructorMethod = m.reflectClass(classType).reflectConstructor(constructor)
                val idTermSymb = ru.typeOf[E].decl(ru.TermName("id")).asTerm
                val e = constructorMethod.apply()
                m.reflect(e).reflectField(idTermSymb).set(1)
        */
        val func = getDeserializer[E](rs)

        results[E](rs)(getDeserializer(rs))
    }

    def results[T](resultSet: ResultSet)(f: ResultSet => T) = {
        new Iterator[T] {
            def hasNext = resultSet.next()

            def next() = f(resultSet)
        }
    }

    def getDeserializer[E: ru.TypeTag](rs: ResultSet): ResultSet => E = {
        _ => {
            // return null
            val m = ru.runtimeMirror(getClass.getClassLoader)
            val classType = ru.typeOf[E].typeSymbol.asClass
            val constructor = typeTag[E].tpe.decl(ru.termNames.CONSTRUCTOR).asMethod
            val constructorMethod = m.reflectClass(classType).reflectConstructor(constructor)
            val idTermSymb = ru.typeOf[E].decl(ru.TermName("id")).asTerm
            val e = constructorMethod.apply()
            m.reflect(e).reflectField(idTermSymb).set(rs.getInt(1))
            e.asInstanceOf[E]
        }
    }

}

