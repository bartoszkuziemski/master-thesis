import random
import string

# Define the possible values for categoryType, conditionType, and offerType
CATEGORY_TYPES = ['ELECTRONICS', 'FASHION', 'HOME_AND_GARDEN', 'FOOD', 'BEAUTY', 'HEALTH', 'SPORT', 'AUTOMOTIVE', 'ART']
CONDITION_TYPES = ['NEW', 'USED', 'NEW_WITHOUT_TAG', 'NEW_WITH_DEFECT']
OFFER_TYPES = ['BUY_NOW', 'AUCTION']

# Generate a random string of length n
def random_string(n):
    return ''.join(random.choices(string.ascii_uppercase + string.digits, k=n))

# Generate an SQL insert statement for a row of data
def generate_sql_insert():
    name = random_string(10)
    price = round(random.uniform(5.00, 5000.00), 2)
    description = random_string(50)
    rating = round(random.uniform(0.00, 5.00), 2)
    user_id = random.randint(1, 100)
    category_type = random.choice(CATEGORY_TYPES)
    condition_type = random.choice(CONDITION_TYPES)
    offer_type = random.choice(OFFER_TYPES)
    return f"INSERT INTO product (name, price, description, rating, user_id, category_type, condition_type, offer_type) VALUES ('{name}', {price}, '{description}', {rating}, {user_id}, '{category_type}', '{condition_type}', '{offer_type}');\n"

# Generate an SQL file with 1000 rows of data
with open('V3.0__insert_products.sql', 'w') as sql_file:
    for i in range(10000):
        sql_insert = generate_sql_insert()
        sql_file.write(sql_insert)
