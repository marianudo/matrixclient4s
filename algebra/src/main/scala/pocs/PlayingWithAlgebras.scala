package pocs

import cats.InjectK
import cats.data.EitherK
import cats.free.Free
import cats.free.Free.liftF

object PlayingWithAlgebras {

  object GeneratorDsl {
    sealed abstract class Generator[A]
    case object IntGen    extends Generator[Int]
    case object StringGen extends Generator[String]

    type GeneratorOp[A] = Free[Generator, A]

    def genInt    = liftF(IntGen)
    def genString = liftF(StringGen)
  }

  object ConcatDsl {
    sealed abstract class Concatenator[A]
    case class ConcatInt(i1: Int, i2: Int)          extends Concatenator[Int]
    case class ConcatString(s1: String, s2: String) extends Concatenator[String]

    type ConcatenatorOp[A] = Free[Concatenator, A]

    def concatInt(i1: Int, i2: Int)          = liftF(ConcatInt(i1, i2))
    def concatString(s1: String, s2: String) = liftF(ConcatString(s1, s2))
  }

  import ConcatDsl._
  import GeneratorDsl._

  type App[A] = EitherK[Generator, Concatenator, A]

  class Generate[F[_]](implicit ev: InjectK[Generator, F]) {
    def genInt: Free[F, Int]       = Free.inject(IntGen)
    def genString: Free[F, String] = Free.inject(StringGen)
  }

  object Generate {
    def apply[F[_]](implicit ev: InjectK[Generator, F]): Generate[F] = new Generate[F]
  }

  class Concat[F[_]](implicit ev: InjectK[Concatenator, F]) {
    def concatInt(i1: Int, i2: Int): Free[F, Int]             = Free.inject(ConcatInt(i1, i2))
    def concatString(s1: String, s2: String): Free[F, String] = Free.inject(ConcatString(s1, s2))
  }

  object Concat {
    def apply[F[_]](implicit ev: InjectK[Concatenator, F]): Concat[F] = new Concat[F]
  }

  def program(implicit gen: Generate[App], concat: Concat[App]) = {
    import gen._, concat._

    for {
      i1 <- genInt
      i2 <- genInt
      s1 <- genString
      s2 <- genString
      it <- concatInt(i1, i2)
      st <- concatString(s1, s2)
    } yield (it, st)
  }

  object Interpreter
}
