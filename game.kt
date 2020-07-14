package minesweeper
import java.util.*
import kotlin.random.Random

fun main() {
    var userminefield = arrayOf(
        CharArray(9),
        CharArray(9),
        CharArray(9),
        CharArray(9),
        CharArray(9),
        CharArray(9),
        CharArray(9),
        CharArray(9),
        CharArray(9)
    )
    var minefield = arrayOf(
        CharArray(9),
        CharArray(9),
        CharArray(9),
        CharArray(9),
        CharArray(9),
        CharArray(9),
        CharArray(9),
        CharArray(9),
        CharArray(9)
    )
    var minelocation = arrayOf(
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
                IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2)
    )
    println("How many mines do you want on the field?")
    val totalmines = readLine()!!.toInt()
//    var totalmines = 2

    for (i in 0..8) {
        for (j in 0..8) {
            minefield[i][j] = '.'
        }
    }
    for (i in 0..8) {
        for (j in 0..8) {
            userminefield[i][j] = '.'
        }
    }

    printfield(userminefield)
    var userinput:Array<String> = arrayOf("a", "a")



    var q = 0
    loop@ do {

         var count1 = 0
         var count2 = 0
         for (i in 0..8) {
             for (j in 0..8) {
                 if (userminefield[i][j] == '*') {
                     count1++
                 } else if (userminefield[i][j] == '.') {
                     count2++
                 }
             }
         }
         if (totalmines == count1 || totalmines == count2) {
             print("Congratulations! You found all the mines!")
             break@loop
         } else {
             if(q == 0) {
                 println("Set/unset mines marks or claim a cell as free:")
                 userinput = readLine()!!.split(" ").toTypedArray()
                 randommine(minefield, totalmines, userinput, minelocation)
                 minesaround(minefield, totalmines, minelocation)
             } else {
                 println("Set/unset mines marks or claim a cell as free:")
                 userinput = readLine()!!.split(" ").toTypedArray()
             }
             when (userinput[2]) {
                 "free" -> {
                     if (minefield[userinput[1].toInt() - 1][userinput[0].toInt() - 1].toInt() in 48..56) {
                         userminefield[userinput[1].toInt() - 1][userinput[0].toInt() - 1] = minefield[userinput[1].toInt() - 1][userinput[0].toInt() - 1]
                     } else if (minefield[userinput[1].toInt() - 1][userinput[0].toInt() - 1] == '.'){
                      // userminefield[userinput[1].toInt() - 1][userinput[0].toInt() - 1] = '/'
                         minefield[userinput[1].toInt() - 1][userinput[0].toInt() - 1] = '/'
                         freearound(minefield, userminefield)
                         userminefield[userinput[1].toInt() - 1][userinput[0].toInt() - 1] = '/'
                     } else {
                         var p = 0
                         while(p < totalmines) {
                             userminefield[minelocation[p][0]][minelocation[p][1]] = 'X'
                             p++
                         }
                         printfield(userminefield)
                         println("You stepped on a mine and failed!")
                         break@loop
                     }
                     printfield(userminefield)
                     println()
                 }
                 "mine" -> {
                     if (userminefield[userinput[1].toInt() - 1][userinput[0].toInt() - 1] == '*') {
                         userminefield[userinput[1].toInt() - 1][userinput[0].toInt() - 1] = '.'
                     } else {
                         userminefield[userinput[1].toInt() - 1][userinput[0].toInt() - 1] = '*'
                     }

                     printfield(userminefield)
                     println()
                 }
             }
         }
         q++
     } while (q in 0..81)


//printfield(userminefield)
//        printfield(minefield)
}


fun minecounter(a: Array<CharArray>, b: Int, c: Int) {
    if (a[b][c] == '.') {
        a[b][c] = a[b][c] + 3
    } else if (a[b][c] == 'X') {

    }
    else {
        a[b][c]++
    }
}

fun freecounter(minefield: Array<CharArray>, userminefield: Array<CharArray>, b: Int, c: Int) {
    if (minefield[b][c] == '.') {
        minefield[b][c] = '/'
        userminefield[b][c] = '/'
  } else if (minefield[b][c].toInt() in 48..56 ) {
       userminefield[b][c] = minefield[b][c]
   }
}


fun freearound(minefield: Array<CharArray>, userminefield: Array<CharArray>) {
    var i = 0
    var j = 0
    var p = 0
    while(p < 81) {
        for (l in 0..8) {
            for (m in 0..8) {
                if (minefield[l][m] == '/') {
                    i = l
                    j = m
                }
                //mid-area
                if (i in 1..7 && j in 1..7) {
                    freecounter(minefield, userminefield, i - 1, j - 1)                //1
                    freecounter(minefield, userminefield, i, j - 1)                    //2
                    freecounter(minefield, userminefield, i + 1, j - 1)               //3
                    freecounter(minefield, userminefield, i - 1, j)                    //4
                    freecounter(minefield, userminefield, i + 1, j)                      //5
                    freecounter(minefield, userminefield, i - 1, j + 1)                //6
                    freecounter(minefield, userminefield, i, j + 1)                  //7
                    freecounter(minefield, userminefield, i + 1, j + 1)                //8
                }


                //top row

                if (j in 1..7 && i == 0) {
                    freecounter(minefield, userminefield, i, j - 1)                    //2
                    freecounter(minefield, userminefield, i + 1, j - 1)               //3
                    freecounter(minefield, userminefield, i + 1, j)                    //5
                    freecounter(minefield, userminefield, i, j + 1)                    //7
                    freecounter(minefield, userminefield, i + 1, j + 1)               //8
                }

//bottom row
                if (j in 1..7 && i == 8) {
                    freecounter(minefield, userminefield, i - 1, j - 1)                //1
                    freecounter(minefield, userminefield, i, j - 1)                    //2
                    freecounter(minefield, userminefield, i - 1, j)                    //4
                    freecounter(minefield, userminefield, i - 1, j + 1)                //6
                    freecounter(minefield, userminefield, i, j + 1)                  //7
                }


                //left column
                if (i in 1..7 && j == 0) {
                    freecounter(minefield, userminefield, i - 1, j)                    //4
                    freecounter(minefield, userminefield, i + 1, j)                      //5
                    freecounter(minefield, userminefield, i - 1, j + 1)                //6
                    freecounter(minefield, userminefield, i, j + 1)                  //7
                    freecounter(minefield, userminefield, i + 1, j + 1)                //8
                }

// right column
                if (i in 1..7 && j == 8) {
                    freecounter(minefield, userminefield, i - 1, j - 1)                //1
                    freecounter(minefield, userminefield, i, j - 1)                    //2
                    freecounter(minefield, userminefield, i + 1, j - 1)               //3
                    freecounter(minefield, userminefield, i - 1, j)                    //4
                    freecounter(minefield, userminefield, i + 1, j)                      //5
                }

                //top left corner
                if (i == 0 && j == 0) {
                    freecounter(minefield, userminefield, i + 1, j)                    //5
                    freecounter(minefield, userminefield, i, j + 1)                    //7
                    freecounter(minefield, userminefield, i + 1, j + 1)               //8
                }


                //bottom left corner
                if (i == 8 && j == 0) {
                    freecounter(minefield, userminefield, i - 1, j)                    //4
                    freecounter(minefield, userminefield, i - 1, j + 1)                //6
                    freecounter(minefield, userminefield, i, j + 1)                  //7
                }

                //top right corner
                if (i == 0 && j == 8) {
                    freecounter(minefield, userminefield, i, j - 1)                    //2
                    freecounter(minefield, userminefield, i + 1, j - 1)               //3
                    freecounter(minefield, userminefield, i + 1, j)                      //5
                }

                //bottom right corner
                if (i == 8 && j == 8) {
                    freecounter(minefield, userminefield, i - 1, j - 1)                //1
                    freecounter(minefield, userminefield, i, j - 1)                    //2
                    freecounter(minefield, userminefield, i - 1, j)                    //4
                }

            }
        }
   p++
    }
    }


fun randommine(minefield: Array<CharArray>, totalmines: Int, userinput: Array<String>, minelocation: Array<IntArray>) {
    var k = 0
    while (k < totalmines) {
        val rowrand1 = Random.nextInt(89)
        val colrand2 = Random.nextInt(89)
        var rowrand = rowrand1/10
        var colrand = colrand2/10
        if (colrand == userinput[1].toInt() - 1 && rowrand == userinput[0].toInt() - 1) {
            continue
        } else if (minefield[colrand][rowrand] == 'X') {
            continue
        } else {
//            minefield[1][1] = 'X'
//            minefield[1][6] = 'X'
//            minefield[1][3] = 'X'
//            minefield[3][2] = 'X'
//            minefield[4][5] = 'X'
//            minefield[7][8] = 'X'
          minefield[colrand][rowrand] = 'X'
            minelocation[k][0] = colrand
            minelocation[k][1] = rowrand
            k++
        }
    }
}

fun minesaround(minefield: Array<CharArray>, totalmines: Int, minelocation: Array<IntArray>) {
    var i = 0
    var j = 0
    var p = 0
    while (p < totalmines) {
        i = minelocation[p][0]
        j = minelocation[p][1]
        //mid-area
        if (i in 1..7 && j in 1..7) {
            minecounter(minefield, i - 1, j - 1)                //1
            minecounter(minefield, i, j - 1)                    //2
            minecounter(minefield, i + 1, j - 1)               //3
            minecounter(minefield, i - 1, j)                    //4
            minecounter(minefield, i + 1, j)                      //5
            minecounter(minefield, i - 1, j + 1)                //6
            minecounter(minefield, i, j + 1)                  //7
            minecounter(minefield, i + 1, j + 1)                //8
        }


        //top row

        if (j in 1..7 && i == 0) {
            minecounter(minefield, i, j - 1)                    //2
            minecounter(minefield, i + 1, j - 1)               //3
            minecounter(minefield, i + 1, j)                    //5
            minecounter(minefield, i, j + 1)                    //7
            minecounter(minefield, i + 1, j + 1)               //8
        }

//bottom row
        if (j in 1..7 && i == 8) {
            minecounter(minefield, i - 1, j - 1)                //1
            minecounter(minefield, i, j - 1)                    //2
            minecounter(minefield, i - 1, j)                    //4
            minecounter(minefield, i - 1, j + 1)                //6
            minecounter(minefield, i, j + 1)                  //7
        }


        //left column
        if (i in 1..7 && j == 0) {
            minecounter(minefield, i - 1, j)                    //4
            minecounter(minefield, i + 1, j)                      //5
            minecounter(minefield, i - 1, j + 1)                //6
            minecounter(minefield, i, j + 1)                  //7
            minecounter(minefield, i + 1, j + 1)                //8
        }

// right column
        if (i in 1..7 && j == 8) {
            minecounter(minefield, i - 1, j - 1)                //1
            minecounter(minefield, i, j - 1)                    //2
            minecounter(minefield, i + 1, j - 1)               //3
            minecounter(minefield, i - 1, j)                    //4
            minecounter(minefield, i + 1, j)                      //5
        }

        //top left corner
        if (i == 0 && j == 0) {
            minecounter(minefield, i + 1, j)                    //5
            minecounter(minefield, i, j + 1)                    //7
            minecounter(minefield, i + 1, j + 1)               //8
        }


        //bottom left corner
        if (i == 8 && j == 0) {
            minecounter(minefield, i - 1, j)                    //4
            minecounter(minefield, i - 1, j + 1)                //6
            minecounter(minefield, i, j + 1)                  //7
        }

        //top right corner
        if (i == 0 && j == 8) {
            minecounter(minefield, i, j - 1)                    //2
            minecounter(minefield, i + 1, j - 1)               //3
            minecounter(minefield, i + 1, j)                      //5
        }

        //bottom right corner
        if (i == 8 && j == 8) {
            minecounter(minefield, i - 1, j - 1)                //1
            minecounter(minefield, i, j - 1)                    //2
            minecounter(minefield, i - 1, j)                    //4
        }
p++
    }
}



fun printfield(userminefield: Array<CharArray>) {
    println(" │123456789│")
    println("—│—————————│")
    for (i in 0..8) {
        print("${i + 1}" + "|")
        for (j in 0..8) {
            print("${userminefield[i][j]}")
        }
        print("|")
        println()
    }
    println("—│—————————│")
}
