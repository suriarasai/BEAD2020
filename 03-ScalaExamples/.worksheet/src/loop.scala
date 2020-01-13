object loop {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(57); 
  println("Welcome to the Scala worksheet");$skip(13); 

  var r = 1;System.out.println("""r  : Int = """ + $show(r ));$skip(13); 
  var n = 10;System.out.println("""n  : Int = """ + $show(n ));$skip(48); 

  while (n > 0) {
    r = r * n
    n -= 1
  };$skip(14); 

  println(r);$skip(9); 

  r = 1;$skip(9); 
  n = 10;$skip(35); 

  for (i <- 1 to n)
    r = r * i;$skip(14); 

  println(r);$skip(19); 

  val s = "Hello";System.out.println("""s  : String = """ + $show(s ));$skip(14); 
  var sum = 0;System.out.println("""sum  : Int = """ + $show(sum ));$skip(47); 
  for (i <- 0 to s.length - 1)
    sum += s(i);$skip(16); 

  println(sum);$skip(11); 

  sum = 0;$skip(32); 
  for (ch <- "Hello") sum += ch

  import scala.util.control.Breaks._;$skip(169); 
  breakable {
    for (ch <- "Hello World") {
      if (ch == ' ') break; // Exits the breakable block
      println(ch)
    }
  };$skip(60); 

  for (i <- 1 to 3; j <- 1 to 3) print((10 * i + j) + " ");$skip(70); 

  for (i <- 1 to 3; j <- 1 to 3 if i != j) print((10 * i + j) + " ");$skip(77); 

  for (i <- 1 to 3; from = 4 - i; j <- from to 3) print((10 * i + j) + " ");$skip(90); 
  for {
    i <- 1 to 3
    from = 4 - i
    j <- from to 3
  } print((10 * i + j) + " ");$skip(34); val res$0 = 

  for (i <- 1 to 10) yield i % 3;System.out.println("""res0: scala.collection.immutable.IndexedSeq[Int] = """ + $show(res$0));$skip(56); val res$1 = 

  for (c <- "Hello"; i <- 0 to 1) yield (c + i).toChar;System.out.println("""res1: String = """ + $show(res$1));$skip(56); val res$2 = 

  for (i <- 0 to 1; c <- "Hello") yield (c + i).toChar;System.out.println("""res2: scala.collection.immutable.IndexedSeq[Char] = """ + $show(res$2))}

}
