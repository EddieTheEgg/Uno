import random

MAX_LINES = 3 #constant global value that will not change
MAX_BET = 100
MIN_BET = 1

ROWS = 3
COLS = 3

symbol_dictionary = {  #example of a dictionary where we store our random symbols. This is sort of like our reference area hence dictionary. 
    "A":3, # A is what is identified as the symbol, and in this dictionary there are "2" counts of it
    "B":5, # B is what is identified as the symbol, and in this dict there are "4" counts of it
    "C":9,
    "D":10
}

def get_spin(ROWS,COLS,symbol_dictionary):
   #Part 1: Getting all the symbols possible from dictionary symbol_count and putting them into a list to be used.
    all_symbols=[]   #This is new list where we will store all symbols after going through the DICTIONARY symbol_count.

    for (symbol, x) in symbol_dictionary.items(): #items give you access to dictionary key and value. Ex): symbol is A, x is 2. We have multiple variables for arrays when we have two-dimensional or more than one pair arrays.
        for _ in range (x):  #range is known as another way to say how many times do you want to repeat this?
            all_symbols.append(symbol)  #Depending on x, we will add the symbol a certain amount of times. Like we will add A,A into list all_symbols because in the dict we are given there are 2 counts of it

    # repeat until all symbols from the dictionary is added into the list all_symbols

    #Part 2: Choosing random symbols based on the elements in our all_symbols list.
    COLUMNS = [] #Another new list, storing each column with random symbols generated into this list
    for _ in range(COLS): #we are given the range in our global COLS variable
        column = [] #For every column, we will generate a list based on the range of columns above
        current_symbols=all_symbols [:] #This is a slice operator, which copies a list without affecting each other in the future. So if I remove values from list current_symbols, we will not affect all_symbols and lose values.

        for _ in range(ROWS): #For each row given by our global variable ROWS, we will pick a random value and add into our column list
            value = random.choice(current_symbols)  #gets a random value from the list current_symbols
            current_symbols.remove(value) #this removes the random value picked away from current_symbols
            column.append(value) #this adds the random value into generated column list
            
    #repeat until all the rows in that one column are filled up

        COLUMNS.append(column) #after all rows in the column list fills up, then we can add this column into COLUMNS list

    return COLUMNS 
    #COLUMNS LIST: ([A,B,B] , [C,C,D])
    #Column list: [A,B,B]

#What we are basically doing is accessing COLUMNS list, and picking the first element of each column list and then loop this with the second element, then third. 
#So we are getting ONE element from each column list for EVERY row. So above would be [A,C], then [B,C], then [B,D]

def print_slot_machine_columns(COLUMNS):  # access get_spin COLUMNS for this function
    for row in range(len(COLUMNS[0])): # 0 is a placeholder to access the first column list within the MAIN LIST COLUMNS. We know each column list has 3 elements so now each row will repeat 3 times.
        for (i, column) in enumerate(COLUMNS):   # i, is the index, and column is the item (in this case column list is the item) associatd with the index i as we loop through. Enumerate makes the COLUMNS list turn into an index.
            if i != len(COLUMNS)-1: # this conditional statement checks if we need to add a pipe line to the last symbol of the COLUMNS list. 
                print (column[row], end=" | ")   #So goes to COLUMNS list -> gets column list->gets the element at whatever value row is.
            else: 
                print (column[row], end=" ")
        else:
            print()

#int is not an interable DATA, so like when accessing lists, we need to use another variable that's not an int to itierate

def deposit():   # this function gets the user input for how much money they want to deposit in to this game
    
    while True: # this is how while loops mostly start, has to be true to check below
        amount = input("Please input a certain numerical amount: $")  #input function is like scanner, requests users to deposit
        if amount.isdigit():   #isnumeric() works too, as long as the str object does not contain a decimal dot or any other non-numerical char
            amount = int(amount)
            if amount > 0:
                break   #like java, break out of loop
            else:
                print ("Amount must be greater than 0")  
        else:
            print ("Please input a numerical number!")  
    return amount #this is the output value when we call this function: "Deposit()"

def get_number_of_lines(): #this function gets the user input for the amount of lines they want to bet on
    while True:
        lines = input("Enter how many lines you want to bet on (1-" + str(MAX_LINES) + ") ?") #one way to embed different variables together
        if lines.isdigit():
            lines = int(lines)
            if 1 <= lines <= MAX_LINES:
                break
            else:
                print("Please input a number greater than 1 or less than or equal to maximum lines") 
        else:
            print("Please input numerical number!")
    return lines

def get_bet(): #this function gathers the user amount for betting between min and max bet value
    while True:
        amount = input("How much would you like to bet on each line?") 
        if amount.isdigit():
            amount = int(amount)
            if MIN_BET <= amount <= MAX_BET:
                break
            else:
                print(f"Please input an amount between ${MIN_BET} and ${MAX_BET}") #another way to embed using lowercase f to embed different variables types within strings
        else:
            print("Please input a numerical number")
    return amount



def main(): #this function calls the previous two methods and sets these local variabels to the return values of those functions
    balance = deposit()  #calls the deposit function and gets the return value and set it to balance
    lines = get_number_of_lines() # same thing as above balance
    while True: #loop statement that forces user to bet a certain amount until they actually bet a valid amount
        bet = get_bet()
        total_bet= bet * lines
        if total_bet > balance:
            print(f"Please bet an amount less than or equal to your balance! You want to bet ${total_bet} but your current balance is ${balance}")
        else:
            print(f"You are betting ${bet} on {lines} lines. Your total bet is ${total_bet}")
            break
main() #since this def function doesn't return any value like a java void method, we just close it with its original name
 

slots = get_spin(ROWS,COLS,symbol_dictionary)
print_slot_machine_columns(slots)