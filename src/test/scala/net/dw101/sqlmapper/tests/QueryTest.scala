package net.dw101.sqlmapper.tests

import net.dw101.sqlmapper.{CommandDefinition, SqlMapper}
import org.apache.commons.dbcp2.BasicDataSource
import org.scalatest.FlatSpec

class QueryTest extends FlatSpec {
    "data source" should "load" in {
        import com.typesafe.config.ConfigFactory
        val conf = ConfigFactory.load
        val ds = new BasicDataSource
        ds.setUsername(conf.getString("db.username"))
        ds.setPassword(conf.getString("db.username"))
        ds.setDriverClassName(conf.getString("db.driver"))
        ds.setUrl(conf.getString("db.url"))
        val conn = ds.getConnection()
        val stmt = conn.createStatement()
        val rs = stmt.executeQuery("select 1 id union select 2")
        assert(rs.next())
        assert(rs.getInt(1) == 1)
        assert(rs.next())
        assert(rs.getInt(1) == 2)
        assert(!rs.next())
        conn.close()
        assert(conn.isClosed)
    }

    "query" should "be fine" in {
        import com.typesafe.config.ConfigFactory
        val conf = ConfigFactory.load
        val ds = new BasicDataSource
        ds.setUsername(conf.getString("db.username"))
        ds.setPassword(conf.getString("db.username"))
        ds.setDriverClassName(conf.getString("db.driver"))
        ds.setUrl(conf.getString("db.url"))
        val cmd = new CommandDefinition
        cmd.commandText = "select 1 id, 'tom' name union select 2,'mike'"
        val persons = SqlMapper.query[Person](ds, cmd)
        for (p <- persons) {
            println(p.id)
            assert(p.id != 0)
        }
    }



//    "sql" should "" in {
//        sql"""aa"""
//    }
}

class Person {
    var id: Int = 0
    var name: String = ""
}
