# TEAM 4 CAPSTONE 1 LET'S GO
- How are we gonna approach this? 
  - Pair programming work on vertical slices seems like the way to go. That way we won't get off track. 
  - Write unit tests as we move forward.
  - Use Git to track changes to this document. 

- What are the slices gonna be?
  - BRICK BY BRICK. One requirement at a time, and refactor as we go when necessary.

## Data Model Classes
### CandyStoreItem 
- String id;
- String name;  
- boolean isIndividuallyWrapped; 
- int stockCount = 100; 
- double price;

- Constructor needs take values from our inventory input file.
- Need two constructors: one that takes everything, and one that takes everything except stock count. 
- Need 
#### Chocolate 
- extends CandyStoreItem

#### Sour
- extends CandyStoreItem

#### Licorice
- extends CandyStoreItem

#### HardCandy
- extends CandyStoreItem


## Controller Classes
### CandyStore
- This is our god class. It's what will handle menu I/O, file I/O, and data class operations.  
- All the major methods need to live here.
  - showInventory
    - Is this where we put our method to ask for an inventory file and load it, or should that happen even earlier? 
  - makeSale
  - quit
  - 
- 

## View Classes
### Menu
- 
