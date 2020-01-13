object simplefunctions {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(68); 
  println("Welcome to the Scala worksheet");$skip(45); 
  def abs(x: Double) = if (x >= 0) x else -x;System.out.println("""abs: (x: Double)Double""");$skip(19); 

  println(abs(3));$skip(19); 
  println(abs(-3));$skip(79); 

  def fac(n: Int) = {
    var r = 1
    for (i <- 1 to n) r = r * i
    r
  };System.out.println("""fac: (n: Int)Int""");$skip(20); 

  println(fac(10));$skip(82); 

  def recursiveFac(n: Int): Int =
    if (n <= 0) 1 else n * recursiveFac(n - 1);System.out.println("""recursiveFac: (n: Int)Int""");$skip(29); 

  println(recursiveFac(10));$skip(97); 
  
  
  
def decorate(str: String, left: String = "[", right: String = "]") =
left + str + right;System.out.println("""decorate: (str: String, left: String, right: String)String""");$skip(28); 

println(decorate("Hello"));$skip(42); 

println(decorate("Hello", "<<<", ">>>"));$skip(36); 

println(decorate("Hello", ">>>["));$skip(63); 

println(decorate(left = "<<<", str = "Hello", right = ">>>"));$skip(44); 

println(decorate("Hello", right = "]<<<"))}
  

}
