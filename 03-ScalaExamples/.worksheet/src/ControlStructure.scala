object ControlStructure {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(69); 
  println("Welcome to the Scala worksheet");$skip(43); 
  // Conditional Expresssions
  val xx = 0;System.out.println("""xx  : Int = """ + $show(xx ));$skip(25); val res$0 = 

  if (xx > 0) 1 else -1;System.out.println("""res0: Int = """ + $show(res$0));$skip(33); 

  val s = if (xx > 0) 1 else -1;System.out.println("""s  : Int = """ + $show(s ));$skip(34); val res$1 = 

  if (xx > 0) "positive" else -1;System.out.println("""res1: Any = """ + $show(res$1));$skip(17); val res$2 = 

  if (xx > 0) 1;System.out.println("""res2: AnyVal = """ + $show(res$2));$skip(40); 

  //Statement Termination
  val n = 10;System.out.println("""n  : Int = """ + $show(n ));$skip(12); 
  val r = 1;System.out.println("""r  : Int = """ + $show(r ));$skip(241); 

  //if (n > 0) { r = r * n; n -= 1 }

  //val s, s0, v, v0, t = 0.0

  //s = s0 + (v - v0) * t + // The + tells the parser that this is not the end
  //0.5 * (a - a0) * t * t

  //if (n > 0) {
  //r = r * n
  //n -= 1
  //}

  val x0 = 1.0;System.out.println("""x0  : Double = """ + $show(x0 ));$skip(15); 
  val y0 = 1.0;System.out.println("""y0  : Double = """ + $show(y0 ));$skip(14); 
  var x = 4.0;System.out.println("""x  : Double = """ + $show(x ));$skip(14); 
  var y = 5.0

  import scala.math._;System.out.println("""y  : Double = """ + $show(y ));$skip(103); 

  val distance = { val dx = x - x0; val dy = y - y0; sqrt(dx * dx + dy * dy) };System.out.println("""distance  : Double = """ + $show(distance ));$skip(136); 

  //var r = 1
  //var n = 10 { r = r * n; n -= 1 } // Has value Unit

  //x = y = 1 // No--can't assign Unit to x

  print("Answer: ");$skip(14); 
  println(42);$skip(28); 

  println("Answer: " + 42);$skip(60); 

  printf("Hello, %s! You are %d years old.%n", "Fred", 42);$skip(21); 

  val name = "Fred";System.out.println("""name  : String = """ + $show(name ));$skip(22); 
  val age = 42.333333

  //print(f"Hello, $name! You are $age%7.2f years old%n")

  import scala.io;System.out.println("""age  : Double = """ + $show(age ));$skip(147); 
 // val name2 = StdIn.readLine("Your name: ")

  print("Your age: ")}
 // val age2 = StdIn.readInt()

 // println(f"Hello, $name2! Next year, you will be ${age2 + 1}.", name, age + 1)
 

}
