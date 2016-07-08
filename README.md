
Basic usage :

Prepare a .tex file, inserting into it the starting equation.
Make sure that it is in following format :
%les yourEquationHere;

TEX-style notation is somewhat supported - the parser will handle \frac{}{}, \cdot, \sqrt{} et cetera, but there may still be unsupported features.

You may also append the variable that you wish to find to the notation, like so :
%les y+x=0;y;

In absence of user-specified variable, x is used.

To run LES on a file thus prepared, just run release/les.jar with the file as an argument. Multiple equations can be processed at once - ensure to put each on its own line, prefaced with %les etc.

This project is still very much a wip and may enter infinite loops when faced with a quadratic equation. Checking for unsafe steps is not yet implemented, incorrect results are therefore a possibility.
