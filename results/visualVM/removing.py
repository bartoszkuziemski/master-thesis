import csv

# Open the input and output CSV files
with open('endurance.csv', 'r') as input_file, open('endurance_test_spring.csv', 'w', newline='') as output_file:

    # Create a CSV reader for the input file
    reader = csv.reader(input_file)

    # Create a CSV writer for the output file
    writer = csv.writer(output_file)

    # Loop through each row in the input file
    for i, row in enumerate(reader):

        # Only write every 10th row to the output file
        if i % 10 == 0:
            writer.writerow(row)
