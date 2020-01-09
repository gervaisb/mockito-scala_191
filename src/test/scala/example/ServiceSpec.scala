package example

import example.Service.{ExpectedParameterType, OtherParameterType}
import org.mockito.scalatest.IdiomaticMockito
import org.scalatest._

class ServiceSpec extends WordSpec with MustMatchers with IdiomaticMockito {
  "stubbed answer" must {

    val subject = mock[Service]
    subject.init returns mock[Service.State]("My mock for State")
    subject.execute(*, *) answers { (s: Service.State, c: Service.Parameter) =>
      c match {
        case ExpectedParameterType(_) => Right(true)
        case o => Left(new Exception(s"Got $o"))
      }
    }

    "be verified with argMatching" in {
      val state = subject.init()
      subject.execute(state, OtherParameterType("Other"))
      subject.execute(state, ExpectedParameterType("Expected"))

      subject.execute(*, argMatching({ case (_, _: ExpectedParameterType, _) => })) was called
    }
  }
}
