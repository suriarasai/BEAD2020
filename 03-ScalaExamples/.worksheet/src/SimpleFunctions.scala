object SimpleFunctions {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(68); 
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

  println(recursiveFac(10));$skip(95); 

  def decorate(str: String, left: String = "[", right: String = "]") =
    left + str + right;System.out.println("""decorate: (str: String, left: String, right: String)String""");$skip(30); 

  println(decorate("Hello"));$skip(44); 

  println(decorate("Hello", "<<<", ">>>"));$skip(38); 

  println(decorate("Hello", ">>>["));$skip(65); 

  println(decorate(left = "<<<", str = "Hello", right = ">>>"));$skip(46); 

  println(decorate("Hello", right = "]<<<"));$skip(91); 
  
  def sum(args: Int*) = {
  var result = 0
  for (arg <- args) result += arg
  result
};System.out.println("""sum: (args: Int*)Int""");$skip(30); 

val s = sum(1, 4, 9, 16, 25);System.out.println("""s  : Int = """ + $show(s ));$skip(11); 
println(s);$skip(26); 

val s2 = sum(1 to 5: _*);System.out.println("""s2  : Int = """ + $show(s2 ));$skip(12); 
println(s2);$skip(116); 

def recursiveSum(args: Int*) : Int = {
  if (args.length == 0) 0
  else args.head + recursiveSum(args.tail : _*)
};System.out.println("""recursiveSum: (args: Int*)Int""");$skip(40); 

println(recursiveSum(1, 4, 9, 16, 25))

import java.text.MessageFormat;$skip(155); 

val str = MessageFormat.format("The answer to {0} is {1}", "everything",
                        42.asInstanceOf[AnyRef]);System.out.println("""str  : String = """ + $show(str ));$skip(13); 
println(str)}
  

}
