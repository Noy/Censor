package me.noy.censor

/**
  * Created by noy on 09/01/2017.
  */
object Censor {

  // Let's save our words in a map
  val replacementWords = Map( "worst" -> "best", "ever" -> "in existence" )

  // Creating our 'censor' trait with our change method
  trait Censor {
    // Our change method, taking a string and a map[string, string]
    def change(text: String, filters: Map[String, String]): String = {
      // Replace the string with the value in the map
      (text /: filters) { (str, filter) => str.replaceAll(filter._1, filter._2) }
    }
  }

  class ThisAwfulProject extends Censor

  // Use the replacementWords variable
  print(new ThisAwfulProject().change("This is the worst scala project ever", replacementWords))

  def main(args: Array[String]): Unit = {
    // From a txt file, had to do research, make sure your file is in the root
    val lines = scala.io.Source.fromFile("replacements.txt").getLines()
    // get the words from the file
    var words = Map[String,String]()
    // def to change the words
    def change(arr: Array[String]):(String, String) = { arr(0) -> arr(1) }
    // go through the file and find the specific words, replace them with ->
    lines.foreach(line => words += change(line.split(" -> ")))
    // Our statement
    print(new ThisAwfulProject().change("I am expecting you to hate this terrible project", words))
  }

  // own print method, cuz y not
  def print(x: String): Unit = {
    println(x)
  }
}
