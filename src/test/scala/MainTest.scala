import org.scalatest._


class MainTest extends FlatSpec{
  behavior of "Final2019"

  it should "join correctly" in {
    val result = MyClass.solution().collect().toSet
    println(result)
    assert(result ===
    Set(("Jane","Jon"), ("Mary","Jon"), ("Jane","Bob"), ("Mary","Bob"), ("Jane","Mary"), ("Jon","Bob")))
  }
}
