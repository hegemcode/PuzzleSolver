<h2><strong>PuzzleSolver</strong></h2>
<p> <strong>PuzzleSolver</strong> is a program that contains 3 different <i>heuristic algorithms</i> with a common goal of solving some puzzles.
</p>
<p> The puzzle is a 4x4 board with non repetible numbers from 1 to 14. The goal is simple, sort the numbers on the board! There are also two '0's which represent where can you move the other numbers, they work like blank gaps. (Last 2 numbers of the board must the the two 0s).
</p>
<p> The solution is provided in the form of an array following a sequence of a number followed by its movement (<strong>N</strong> North, <strong>S</strong> South, <strong>E</strong> East, <strong>O</strong> West)
</p>
<h2><strong>Algorithms</strong></h2>
<p> The three algorithms in this project consist of an implementation of the following algorithms: </p>
<ul>
  <li>Steepest ascent hill climbing</li>
     <li>Simple hill climbing</li>
        <li>Best-first search</li>
</ul>
<h2><strong>Installation</strong></h2>
<p> Nothing new here, since the program was written in Java you have to write the following in your windows terminal after changing to the project directory:
</p>
<pre>
<code>
md out
javac src\main.java -d out -sourcepath src
java -cp out main
</code>
</pre>
