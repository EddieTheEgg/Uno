MAX_LINES = 3 #constant global value that will not change
MAX_BET = 100
MIN_BET = 1


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
    bet = get_bet()
    total_bet= bet * lines
    print(f"You are betting ${bet} on {lines} lines. Your total bet is ${total_bet}")

main() #since this def function doesn't return any value, we just close it with its original name
 

