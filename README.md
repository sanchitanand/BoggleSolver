  Program: Boggle Game Solver

  Author: Sanchit Anand 

  Date: January 2018

  Description: 
  
  Boggle Game Solver is a simple tool that is able to solve Boggle puzzles of arbitary sizes. 
  
  To properly use this program, first run createDictionary, giving a path to 
  your list of accepted words (lowercase) under the variable dictionaryFileName. 
  This creates a prefix tree from the contents of your dictionary,
  and saves it in serial form.
  In the subsequent executions, loadDictionary loads the contents of 
  this tree, and BoggleSolver is used to solve the puzzle. 
  
  The solver itself works by 
  using a Depth First Search to traverse the grid, and the 
  prefix tree to check the validity of the word so far.
  The variable  maxWordLength gives the maximum travel length.
  
  The output is a list of compatible words from the dictionary.
  
  This program is free software: you can redistribute it and/or modify
  it under the terms of the GNU General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.

  This program is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.

  You should have received a copy of the GNU General Public License
  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 