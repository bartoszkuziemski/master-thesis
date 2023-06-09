import csv
import codecs

# Open the CSV file and read the data
with open('data.csv', newline='') as csvfile:
    data_reader = csv.DictReader(csvfile)
    data = [row for row in data_reader]

# Initialize lists to hold data
req_nums = []
req_per_secs = []
errors_percentages = []
average_latencies = []
p50s = []
p75s = []
p90s = []
p99s = []
p99_9s = []
p99_99s = []
p99_999s = []
p100s = []

# Extract data from each row of the CSV file
for row in data:
    req_nums.append(int(row['req_num']))
    req_per_secs.append(req_nums[-1] / (float(row['duration']) / 1000000))
    errors = sum([int(row['err_connect']), int(row['err_read']), int(row['err_write']), int(row['err_status']), int(row['err_timeout'])])
    errors_percentages.append(errors / req_nums[-1] * 100)
    average_latencies.append(float(row['latency_avg']) / 1000)
    p50s.append(float(row['p50']) / 1000)
    p75s.append(float(row['p75']) / 1000)
    p90s.append(float(row['p90']) / 1000)
    p99s.append(float(row['p99']) / 1000)
    p99_9s.append(float(row['p99_9']) / 1000)
    p99_99s.append(float(row['p99_99']) / 1000)
    p99_999s.append(float(row['p99_999']) / 1000)
    p100s.append(float(row['p100']) / 1000)

time = int(data[0]['time'])
connections = int(data[0]['connections'])
rate = int(data[0]['rate'])

# Generate the LaTeX table
table = 'Czas trwania: {:d} min \\\\ \n'.format(time)
table += 'Liczba użytkowników: {:d} \\\\ \n'.format(connections)
table += 'Zadane obciążenie [req/s]: {:d} \\\\ \n'.format(rate)
table += '\\begin{table}\n'
table += '\t\\begin{tabular}{|l|' + 'c|' * len(data) + '}\n'
table += '\t\t\\hline\n'
table += '\t\t & ' + ' & '.join(['Req {}'.format(i+1) for i in range(len(data))]) + ' \\\\ \\hline\n'
table += '\t\t$load_{r}$ [req/s] & ' + ' & '.join(['{:.2f}'.format(x) for x in req_per_secs]) + ' \\\\ \\hline\n'
table += '\t\terror [\%] & ' + ' & '.join(['{:.2f}'.format(x) for x in errors_percentages]) + ' \\\\ \\hline\n'
table += '\t\t$lat_{avg}$ [ms] & ' + ' & '.join(['{:.2f}'.format(x) for x in average_latencies]) + ' \\\\ \\hline\n'
table += '\t\t$P_{50}$ [ms] & ' + ' & '.join(['{:.2f}'.format(x) for x in p50s]) + ' \\\\ \\hline\n'
table += '\t\t$P_{75}$ [ms] & ' + ' & '.join(['{:.2f}'.format(x) for x in p75s]) + ' \\\\ \\hline\n'
table += '\t\t$P_{90}$ [ms] & ' + ' & '.join(['{:.2f}'.format(x) for x in p90s]) + ' \\\\ \\hline\n'
table += '\t\t$P_{99}$ [ms] & ' + ' & '.join(['{:.2f}'.format(x) for x in p99s]) + ' \\\\ \\hline\n'
table += '\t\t$P_{99.9}$ [ms] & ' + ' & '.join(['{:.2f}'.format(x) for x in p99_9s]) + ' \\\\ \\hline\n'
table += '\t\t$P_{99.99}$ [ms] & ' + ' & '.join(['{:.2f}'.format(x) for x in p99_99s]) + ' \\\\ \\hline\n'
table += '\t\t$P_{99.999}$ [ms] & ' + ' & '.join(['{:.2f}'.format(x) for x in p99_999s]) + ' \\\\ \\hline\n'
table += '\t\t$P_{100}$ [ms] & ' + ' & '.join(['{:.2f}'.format(x) for x in p100s]) + ' \\\\ \\hline\n'
table += '\t\\end{tabular}\n'
table += '\t\\centering\n'
table += '\t\\caption{}\n'
table += '\t\\label{result::}\n'
table += '\\end{table}'

# Print the LaTeX table
with codecs.open('output.tex', 'w', 'utf-8') as outfile:
    outfile.write(table)