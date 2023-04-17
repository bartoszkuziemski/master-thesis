import csv
import random
from enum import Enum

class CategoryType(Enum):
    ELECTRONICS = "ELECTRONICS"
    FASHION = "FASHION"
    HOME_AND_GARDEN = "HOME_AND_GARDEN"
    FOOD = "FOOD"
    BEAUTY = "BEAUTY"
    HEALTH = "HEALTH"
    SPORT = "SPORT"
    AUTOMOTIVE = "AUTOMOTIVE"
    ART = "ART"

class ConditionType(Enum):
    NEW = "NEW"
    USED = "USED"
    NEW_WITHOUT_TAG = "NEW_WITHOUT_TAG"
    NEW_WITH_DEFECT = "NEW_WITH_DEFECT"

class OfferType(Enum):
    BUY_NOW = "BUY_NOW"
    AUCTION = "AUCTION"

def generate_url():
    url = "/products?"
    price_min = round(random.uniform(5.00, 5000.00), 2)
    price_max = round(random.uniform(price_min, 5000.00), 2)
    rating_min = round(random.uniform(0.00, 5.00), 2)
    if random.random() > 0.5:
        url += "name=" + random.choice("abcdefghijklmnopqrstuvwxyz") + "&"
    if random.random() > 0.5:
        url += f"priceMin={price_min:.2f}&"
    if random.random() > 0.5:
        url += f"priceMax={price_max:.2f}&"
    if random.random() > 0.5:
        url += f"ratingMin={rating_min:.2f}&"
    if random.random() > 0.5:
        category_types = random.sample(list(CategoryType), k=random.randint(1, len(CategoryType)))
        for category_type in category_types:
            url += f"categoryType={category_type.value}&"
    if random.random() > 0.5:
        condition_types = random.sample(list(ConditionType), k=random.randint(1, len(ConditionType)))
        for condition_type in condition_types:
            url += f"conditionType={condition_type.value}&"
    if random.random() > 0.5:
        offer_types = random.sample(list(OfferType), k=random.randint(1, len(OfferType)))
        for offer_type in offer_types:
            url += f"offerType={offer_type.value}&"
    # Remove the last '&' character
    url = url[:-1]
    return url


num_urls = 1001
with open('urls.txt', mode='w', newline='') as file:
    for i in range(num_urls):
        url = generate_url()
        file.write(url + "\n")
