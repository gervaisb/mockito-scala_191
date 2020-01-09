package example

class Service {
  import Service._
  def init():State = ???
  def execute(s:State, p:Parameter):Either[Any, Boolean] = ???
}

object Service {
  case class State()

  trait Parameter
  case class OtherParameterType(s:String) extends Parameter
  case class ExpectedParameterType(s:String) extends Parameter
}
