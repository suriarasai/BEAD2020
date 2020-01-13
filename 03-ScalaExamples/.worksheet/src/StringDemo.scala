object StringDemo {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(63); 
  println("Welcome to the Scala worksheet")
  import javax.swing.JOptionPane._;$skip(68); 

  println("Defining firstName");$skip(53); 
  val firstName = showInputDialog("Your First Name");System.out.println("""firstName  : String = """ + $show(firstName ));$skip(27); 
  println("Printing once");$skip(21); 
  println(firstName);$skip(28); 
  println("Printing twice");$skip(21); 
  println(firstName);$skip(32); 

  println("Defining lastName");$skip(56); 
  lazy val lastName = showInputDialog("Your Last Name");System.out.println("""lastName: => String = <lazy>""");$skip(27); 
  println("Printing once");$skip(20); 
  println(lastName);$skip(28); 
  println("Printing twice");$skip(20); 
  println(lastName);$skip(27); 

  println("Defining age");$skip(40); 
  def age = showInputDialog("Your Age");System.out.println("""age: => String""");$skip(27); 
  println("Printing once");$skip(15); 
  println(age);$skip(28); 
  println("Printing twice");$skip(15); 
  println(age)}

}
