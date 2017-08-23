package net.dw101.sqlmapper

import scala.collection.mutable

class SqlExecutor(cmdText: String) {

    def execute(params: mutable.HashMap[String, Any]) = ???

    def query[E](params: mutable.HashMap[String, Any]): E = ???

    def update(params: mutable.HashMap[String, Any]): Int = ???

}
