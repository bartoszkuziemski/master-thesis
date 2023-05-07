import csv

with open('input.csv', 'r') as f:
    reader = csv.DictReader(f)
    data = [row for row in reader]

with open('output.tex', 'w') as f:
    f.write("\\begin{table}[!h]\n")
    f.write("\t\\begin{adjustwidth}{-2cm}{-2cm}\n")
    f.write("\t\t\\begin{tabular}{|c|c|c|c|c|c|c|c|}\n")
    f.write("\t\t\t\\hline\n")
    f.write("\t\t\t $load_{s}$ [req/s] & $load_{r}$ [req/s] & $lat_{avg}$ [ms] & $P_{50}$ [ms] & $P_{90}$ [ms] & $P_{99}$ [ms] & $P_{99.9}$ [ms] & $P_{100}$ [ms] \\\\\n")
    f.write("\t\t\t\\hline\n")

    for row in data:
        f.write(f"\t\t\t{row['rate']} & "
                f"{int(row['req_num'])/(float(row['duration'])/1000000):.2f} & "
                f"{float(row['latency_avg'])/1000:.2f} & "
                f"{int(row['p50'])/1000:.2f} & "
                f"{int(row['p90'])/1000:.2f} & "
                f"{int(row['p99'])/1000:.2f} & "
                f"{int(row['p99_9'])/1000:.2f} & "
                f"{int(row['p100'])/1000:.2f} \\\\ \\hline\n")

    f.write("\t\t\\end{tabular}\n")
    f.write("\t\t\\centering\n")
    f.write("\t\t\\caption{}\n")
    f.write("\t\t\\label{result::}\n")
    f.write("\t\\end{adjustwidth}\n")
    f.write("\\end{table}\n")
