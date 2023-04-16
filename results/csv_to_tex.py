import csv
import codecs

# Open the CSV file and read the data
with open('data.csv', newline='') as csvfile:
    data_reader = csv.DictReader(csvfile)
    data = [row for row in data_reader]

# Calculate the required metrics from the data
req_num = int(data[0]['req_num'])
req_per_sec = req_num / (float(data[0]['duration']) / 1000000)
errors = sum([int(data[0]['err_connect']), int(data[0]['err_read']), int(data[0]['err_write']), int(data[0]['err_status']), int(data[0]['err_timeout'])])
errors_percentage = errors / req_num * 100
average_latency = float(data[0]['latency_avg']) / 1000
p50 = float(data[0]['p50']) / 1000
p75 = float(data[0]['p75']) / 1000
p90 = float(data[0]['p90']) / 1000
p99 = float(data[0]['p99']) / 1000
p99_9 = float(data[0]['p99_9']) / 1000
p99_99 = float(data[0]['p99_99']) / 1000
p99_999 = float(data[0]['p99_999']) / 1000
p100 = float(data[0]['p100']) / 1000

time = int(data[0]['time'])
connections = int(data[0]['connections'])
rate = int(data[0]['rate'])

# Generate the LaTeX table
table = 'Czas trwania: {:d} min \\\\ \n'.format(time)
table += 'Liczba użytkowników: {:d} \\\\ \n'.format(connections)
table += 'Zadane obciążenie [req/s]: {:d} \\\\ \n'.format(rate)
table += '\\begin{table}\n'
table += '\t\\begin{tabular}{|l|c|}\n'
table += '\t\t\\hline\n'
table += '\t\t &  \\\\ \\hline\n'
table += '\t\tObciążenie [req/s] & {:.2f} \\\\ \\hline\n'.format(req_per_sec)
table += '\t\tBłędne odpowiedzi [\%] & {} \\\\ \\hline\n'.format(errors_percentage)
table += '\t\tŚrednie opóźnienie [ms] & {:.2f} \\\\ \\hline\n'.format(average_latency)
table += '\t\tP50 [ms] & {:.2f} \\\\ \\hline\n'.format(p50)
table += '\t\tP75 [ms] & {:.2f} \\\\ \\hline\n'.format(p75)
table += '\t\tP90 [ms] & {:.2f} \\\\ \\hline\n'.format(p90)
table += '\t\tP99 [ms] & {:.2f} \\\\ \\hline\n'.format(p99)
table += '\t\tP99.9 [ms] & {:.2f} \\\\ \\hline\n'.format(p99_9)
table += '\t\tP99.99 [ms] & {:.2f} \\\\ \\hline\n'.format(p99_99)
table += '\t\tP99.999 [ms] & {:.2f} \\\\ \\hline\n'.format(p99_999)
table += '\t\tP100 [ms] & {:.2f} \\\\ \\hline\n'.format(p100)
table += '\t\\end{tabular}\n'
table += '\t\\centering\n'
table += '\t\\caption{}\n'
table += '\t\\label{result::}\n'
table += '\\end{table}'

# Print the LaTeX table
with codecs.open('output.tex', 'w', 'utf-8') as outfile:
    outfile.write(table)
