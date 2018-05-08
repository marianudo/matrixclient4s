package matrixclient4s.http

object Http {

  sealed abstract class Scheme

  case object Http extends Scheme

  case object Https extends Scheme

  case class Authority(user: String, password: String)

  /**
    * Represents a HttpRequest URI.
    *
    * @param scheme    scheme of the uri. For example https
    * @param authority authority of the uri. For example: user:pass@google.com:443
    * @param path      path of the uri. For example /books/234
    * @param query     query string of the uri. For example ?page=3&utm_source=campaign
    * @param fragment  fragment of the uri. For example #header1
    */
  case class Uri(
      scheme: Scheme,
      authority: Option[Authority],
      path: String = "",
      query: Map[String, String] = Map.empty,
      fragment: Option[String]
  )

  sealed abstract class HttpMethod[A] {
    def uri: Uri
    def headers
  }

  case class Get[E](uri: Uri) extends HttpMethod

}
