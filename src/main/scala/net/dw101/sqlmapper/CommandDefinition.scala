package net.dw101.sqlmapper

import scala.collection._

class CommandDefinition {
    /**
      * The command to execute. sql or stored-procedure name
      */
    var commandText: String = ""

    /** *
      * The parameters
      */

    var parameter = new mutable.HashMap[String, Any]
}
