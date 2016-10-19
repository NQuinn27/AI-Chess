# Chess Project  

This is a project for NCI BSc(Hons) Computing Year 4 Introduction to Artificial Intelligence.  

## Instructions
This repository is an IntelliJ IDEA project. Check it out and run using IDEA. To compile manually, remove the package declaration from ChessProject.java, and 'javac ChessProject.java;' 'java ChessProject'  

## Completion 
 
### Phase 1 - Game Logic

- [x] White starts, turns alternate.  
- [x] Current player communicated as window title.  

#### Pawn
- [x] Moves only forwards  
- [x] Takes two moves on first turn    
- [x] Takes one move on subsequent turns.    
- [x] Can take an opponent by moving in a diagonal.  
- [x] Can turn into a piece of players choice when reaches the end of the board.  

![Alt text](images/pawn.gif?raw=true "Pawn Movement")

#### Knight  
- [x] Moves in an 'L' shape with 2 and 1 squares respectively. 
- [x] 'Jumps' - i.e doesnt matter if there is a piece in the way.  
- [x] Can take an opponent on landing square.  

![Alt text](images/knight.gif?raw=true "Knight Movement")  

#### Bishop  
- [x] Moves in any diagonal any number of squares.  
- [x] Must be no piece in the way.  
- [x] Can take an opponent on landing square.  

![Alt text](images/bishop.gif?raw=true "Bishop Movement")  

#### Rook  
- [x] Can move vertically or horizontally any number of squares. 
- [x] Must be no piece in the way.  
- [x] Can take an opponent on landing square.  

![Alt text](images/rook.gif?raw=true "Rook Movement") 

#### Queen  
- [x] Can move vertically, horizontally or diagonally any number of squares.  
- [x] Must be no piece in the way. 
- [x] Can take an opponent on landing square.  

![Alt text](images/queen.gif?raw=true "Queen Movement") 

#### King  
- [x] Can move to any square in a 1-square box around starting point.  
- [x] Cannot move to within one square of opponent King.  
- [ ] Cannot move into an opposing check position. 

![Alt text](images/king.gif?raw=true "King Movement") 

### Phase 2 - Artificial Intelligence Agents  
TODO
