import time

user_data = {}
menu = [
    "Mega A", "Mega B", "Mega C", "Mc Spaghetti", "Big McJo",
    "Sausage McJoffin", "Yummy Burger", "Egg McJoffin",
    "Jonald Burger", "Jonald Sundae", "McJo Flurry", "Quarter Poundeer",
]
price = [139, 139, 149, 85, 90, 37, 49, 44, 35, 29, 25, 85]
cart = {}
total = 0
terminate = False
test = ("\n\t\t\t\t\t\t\tPls wait for your receipt...\n\n\t\t\t\t\t\t\tDone! Please Check Your Receipt Below")

print("\t\t\t\t\t\t               WELCOME TO MCJONALDS WALK THRU                ")
print("\t\t\t\t\t===============================================================================")
print("\n\t\t\t\t\t\t\t\tWHAT DO YOU WANT TO ORDER?")
print("\t\t\t\t\t\tPlease Refer To The Menu and Type The Food That You Want To Order")
print("\t\t\t\t\t===============================================================================")

print("\n\n\t\t\t\t\t*******************************************************************************")
print("\t\t\t\t\t||                           MCJONALDS MENU                                  ||")
print("\t\t\t\t\t||___________________________________________________________________________||")
print("\t\t\t\t\t||        Mega Meals         || Price  || Burger/Spaghetti/Desserts || Price ||")
print("\t\t\t\t\t||---------------------------||--------||---------------------------||-------||")
print("\t\t\t\t\t||         'Mega A'          ||        || Mc Spaghetti              || 85PHP ||")
print("\t\t\t\t\t|| 1 pc chicken Mcjo         || 139PHP || Big McJo                  || 90PHP ||")
print("\t\t\t\t\t||   with fries and soup     ||________|| Jonald Burger             || 35PHP ||")
print("\t\t\t\t\t||                           ||        || Sausage McJoffin          || 37PHP ||")
print("\t\t\t\t\t||         'Mega B'          ||        || Yummy Burger              || 49PHP ||")
print("\t\t\t\t\t|| 1pc chicken Mcjo          || 139PHP || Egg McJoffin              || 44PHP ||")
print("\t\t\t\t\t|| with Rice and Mc Spaghetti||________|| Jonald Sundae             || 29PHP ||")
print("\t\t\t\t\t||                           ||        || McJo Flurry               || 25PHP ||")
print("\t\t\t\t\t||         'Mega C'          ||        || Quarter Poundeer          || 85PHP ||")
print("\t\t\t\t\t|| 1 pc chicken Mcjo         || 149PHP ||                           ||       ||")
print("\t\t\t\t\t|| with fries and Jo Flurry  ||________||                           ||       ||")
print("\t\t\t\t\t*******************************************************************************")

end_order = False

while not end_order:
    user_order = input("\n\t\t\t\t\tEnter your order (Enter 'done' to exit): ")

    if user_order == 'done':
        if not cart:
            print("\n\t\t\t\t\tYou haven't ordered anything")
            break
        else:
            end_order = True
            break

    if user_order not in menu:
        print("\n\t\t\t\t\tOrder NOT IN MENU")
        continue

    quantity = int(input("\n\t\t\t\t\tEnter quantity: "))
    item_price = price[menu.index(user_order)]
    subtotal = item_price * quantity

    if user_order in cart:
        cart[user_order]['quantity'] += quantity
        cart[user_order]['subtotal'] += subtotal
    else:
        cart[user_order] = {'quantity': quantity, 'subtotal': subtotal}

    total += subtotal
    print(f"\t\t\t\t\tAdded {user_order} (x{quantity}) to your cart. Current total: {total}")

if total != 0:
    for i in test:
            print(i,end="")
            time.sleep(0.10)
    print("\n\n\t\t\t\t\t\t\t*********** Order Receipt ********")
    for item, details in cart.items():
        print(f"\t\t\t\t\t\t\t {item} (x{details['quantity']}): {details['subtotal']} PHP    ")
    print(f"\n\t\t\t\t\t\t\t Total Amount: {total} PHP")
    print("\t\t\t\t\t\t\t**********************************")

    while True:
        confirm = input("\n\t\t\t\t\tConfirm (Yes or No): ").lower()
        if confirm == "yes":
            break
        elif confirm == "no":
            remove_order = input("\n\t\t\t\t\tChoose the order you want to remove: ")
            if remove_order in cart:
                removed_total = cart[remove_order]['subtotal']
                total -= removed_total
                del cart[remove_order]
                print(f"\t\t\t\t\tRemoved {remove_order}. Updated total: {total}")
            else:
                print("\n\t\t\t\t\tOrder not in your cart")
        else:
            print("\t\t\t\t\tInvalid input")

    while True:
        try:
            money = int(input("\n\t\t\t\t\tEnter your money: "))
            change = money - total
            if change < 0:
                print("\n\t\t\t\t\tInsufficient Money")
            else:
                print(f"\n\t\t\t\t\tYour Change is: {change}")
                terminate = True
                break
        except ValueError:
            print("\n\t\t\t\t\tInvalid input of money")

print("\n\t\t\t\t\tThank You For Buying In McJonalds Walk THRU ^^")
