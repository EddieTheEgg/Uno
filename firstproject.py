MAX_LINES = 3 #constant global value that will not change



def deposit():   # this is like a method, which is to get the user deposit amount
    
    while True: # this is how while loops mostly start, has to be true to check below
        amount = input("Please input a certain numerical amount: $")  #input function is like scanner, requests users to deposit
        if amount.isdigit():   #isnumeric() works too, as long as the str object does not contain a decimal dot or any other non-numerical char
            amount = int(amount)
            if amount > 0:
                break   #like java, break out of the loop if conditions are all satisfied
            else:
                print ("Amount must be greater than 0")  # can't bet with 0 dollars 
        else:
            print ("Please input a numerical amount ONLY")  # Must be all integer chars (Decimal dots don't count)
    return amount #this is like a getter method, which when we call the method "deposit" we get an "amount" value in return (int)

def get_number_of_lines():
    while True:
        lines = input("Enter how many lines you want to bet on (1-" + str(MAX_LINES) + ") ?") #concatnate because it int+string will not work
        if lines.isdigit():
            lines = int(lines)
            if 1 <= lines <= MAX_LINES:
                break
            else:
                print("Please input a number greater than 1 or less than or equal to maximum lines") 
        else:
            print("Please input numerical numbers, not anything else!")
    return lines


def main(): #this def calls the previous two methods and is used when players want to play again
    balance = deposit()  #calls the deposit function and gets the return value and set it to balance
    lines = get_number_of_lines() # same thing as above balance
    print (balance, lines)  # this prints the return values from deposit, and return value from get_numeber_of_lines
main() 


