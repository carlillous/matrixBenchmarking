import os
import subprocess

from utils.matrix_builder import MatrixBuilder
from mm_mr.matrix_multiplication_mr import MatrixMultiplicationMR
from utils.matrix_loader import MatrixLoader
from utils.functions import combine_output_files,clean_output_directory

def run_mapreduce_job(input_file, output_dir,dim):
    mr_job = MatrixMultiplicationMR(args=[input_file, '--output-dir', output_dir,'--m-dimension',str(dim),'--p-dimension',str(dim)])
    with mr_job.make_runner() as runner:
        runner.run()

def main():
    n = 10
    input_filename = 'matrix_input.txt'
    output_directory = 'output'
    output_filename = os.path.join(output_directory, 'part-00000')  # mrjob genera este archivo de salida por defecto

    print("Generando matrices aleatorias...")
    builder = MatrixBuilder(n, filename=input_filename)
    builder.generate_matrices()

    print("Ejecutando el trabajo MapReduce...")
    run_mapreduce_job(input_filename, output_directory,n)
    combine_output_files(output_directory, 'final_output.txt')
    clean_output_directory(output_directory)

    print("Leyendo y mostrando los resultados...")
    if os.path.exists(output_filename):
        reader = MatrixLoader(output_filename)
        reader.get_matrix()
    else:
        print("No se encontraron resultados de MapReduce.")

if __name__ == '__main__':
    main()
