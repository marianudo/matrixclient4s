package matrixclient4s.algebra.client

import matrixclient4s.algebra.client.ClientApi.VersionsData

trait ClientApi[F[_]] {
  def versions: F[VersionsData]
}

object ClientApi {
  case class VersionsData(vs: Seq[String])
}
