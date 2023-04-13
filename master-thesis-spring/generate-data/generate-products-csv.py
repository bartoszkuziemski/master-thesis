import csv
import random
import string

# Define the possible values for categoryType, conditionType, and offerType
CATEGORY_TYPES = ['ELECTRONICS', 'FASHION', 'HOME_AND_GARDEN', 'FOOD', 'BEAUTY', 'HEALTH', 'SPORT', 'AUTOMOTIVE', 'ART']
CONDITION_TYPES = ['NEW', 'USED', 'NEW_WITHOUT_TAG', 'NEW_WITH_DEFECT']
OFFER_TYPES = ['BUY_NOW', 'AUCTION']

# Generate a random string of length n
def random_string(n):
    return ''.join(random.choices(string.ascii_uppercase + string.digits, k=n))

# Generate a random row of data
def generate_row():
    name = random_string(10)
    price = round(random.uniform(5.00, 5000.00), 2)
    description = random_string(50)
    rating = round(random.uniform(0.00, 5.00), 2)
    user = random.randint(1, 100)
    categoryType = random.choice(CATEGORY_TYPES)
    conditionType = random.choice(CONDITION_TYPES)
    offerType = random.choice(OFFER_TYPES)
    return [name, price, description, rating, user, categoryType, conditionType, offerType]

# Generate a CSV file with 1000 rows of data
with open('products.csv', 'w', newline='') as csvfile:
    writer = csv.writer(csvfile)
    writer.writerow(['name', 'price', 'description', 'rating', 'user', 'categoryType', 'conditionType', 'offerType'])
    for i in range(1000):
        row = generate_row()
        writer.writerow(row)
