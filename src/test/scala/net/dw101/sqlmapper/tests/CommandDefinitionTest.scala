package net.dw101.sqlmapper.tests

import net.dw101.sqlmapper.CommandDefinition
import org.scalatest._

import scala.collection.mutable.HashMap

class CommandDefinitionTest extends FlatSpec {
    "commandDefinition values" should "be correct" in {
        val cmd = new CommandDefinition
        cmd.commandText = "select 1"
        cmd.parameter = HashMap("id" -> 1, "name" -> "tom")
        cmd.parameter("age") = 21
        assert(cmd != null)
        assert(cmd.commandText != null)
        assert(cmd.parameter != null)
        assert(cmd.parameter("id") == 1)
    }
}
