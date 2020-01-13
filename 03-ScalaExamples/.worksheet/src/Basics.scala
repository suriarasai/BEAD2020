object Basics {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(60); 

  println("Welcome to the Scala worksheet");$skip(26); 

  val answer = 8 * 5 + 2;System.out.println("""answer  : Int = """ + $show(answer ));$skip(16); val res$0 = 

  0.5 * answer;System.out.println("""res0: Double = """ + $show(res$0));$skip(31); 

  val greeting: String = null;System.out.println("""greeting  : String = """ + $show(greeting ));$skip(62); 

  //answer = 0 // This will give an error

  var counter = 0;System.out.println("""counter  : Int = """ + $show(counter ));$skip(38); 
  counter = 1;$skip(16);  // Ok, can change a var

  var i, j = 0;System.out.println("""i  : Int = """ + $show(i ));System.out.println("""j  : Int = """ + $show(j ));$skip(40); 
  var greeting2, message: String = null;System.out.println("""greeting2  : String = """ + $show(greeting2 ));System.out.println("""message  : String = """ + $show(message ));$skip(30); val res$1 = 

  //1.toString()

  1.to(10);System.out.println("""res1: scala.collection.immutable.Range.Inclusive = """ + $show(res$1));$skip(30); val res$2 = 

  "Hello".intersect("World");System.out.println("""res2: String = """ + $show(res$2));$skip(20); 

  var counter1 = 0;System.out.println("""counter1  : Int = """ + $show(counter1 ));$skip(17); 

  counter1 += 1;$skip(30); 

  val x: BigInt = 1234567890;System.out.println("""x  : BigInt = """ + $show(x ));$skip(13); val res$3 = 

  x * x * x

  import scala.math._;System.out.println("""res3: scala.math.BigInt = """ + $show(res$3));$skip(34); val res$4 = 

  sqrt(2);System.out.println("""res4: Double = """ + $show(res$4));$skip(14); val res$5 = 
  
  1.to(10);System.out.println("""res5: scala.collection.immutable.Range.Inclusive = """ + $show(res$5));$skip(27); val res$6 = 
  
  1.to(10).map(sqrt(_));System.out.println("""res6: scala.collection.immutable.IndexedSeq[Double] = """ + $show(res$6));$skip(15); val res$7 = 
  
  pow(2, 4);System.out.println("""res7: Double = """ + $show(res$7));$skip(13); val res$8 = 
  min(3, Pi);System.out.println("""res8: Double = """ + $show(res$8));$skip(20); val res$9 = 

  "Hello".distinct;System.out.println("""res9: String = """ + $show(res$9));$skip(48); val res$10 = 

  BigInt.probablePrime(100, scala.util.Random);System.out.println("""res10: scala.math.BigInt = """ + $show(res$10));$skip(14); val res$11 = 

  "Hello"(4);System.out.println("""res11: Char = """ + $show(res$11));$skip(20); val res$12 = 

  "Hello".apply(4);System.out.println("""res12: Char = """ + $show(res$12));$skip(24); val res$13 = 

  BigInt("1234567890");System.out.println("""res13: scala.math.BigInt = """ + $show(res$13));$skip(30); val res$14 = 

  BigInt.apply("1234567890");System.out.println("""res14: scala.math.BigInt = """ + $show(res$14));$skip(49); val res$15 = 

  BigInt("1234567890") * BigInt("112358111321");System.out.println("""res15: scala.math.BigInt = """ + $show(res$15));$skip(36); val res$16 = 

  "Hello, World!".count(_.isUpper);System.out.println("""res16: Int = """ + $show(res$16));$skip(46); val res$17 = 
  
  "DinnerTable".containsSlice('i'.to('e'));System.out.println("""res17: Boolean = """ + $show(res$17));$skip(20); val res$18 = 
  
  "Scala".sorted;System.out.println("""res18: String = """ + $show(res$18))}

}
