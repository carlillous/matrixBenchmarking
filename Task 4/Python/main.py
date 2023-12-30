import os
from utils.matrix_builder import MatrixBuilder
from mm_mr.mr_runner import run_mapreduce_job
from utils.matrix_loader import MatrixLoader
from utils.out_treatment import combine_output_files,clean_output_directory

def main():
    n = 64
    input_filename = 'matrix_input.txt'
    output_directory = 'output'

    print("Generando matrices aleatorias...")
    builder = MatrixBuilder(n, filename=input_filename)
    builder.generate_matrices()

    print("Ejecutando el trabajo MapReduce...")
    run_mapreduce_job(input_filename, output_directory,n)
    combine_output_files(output_directory, 'final_output.txt')

    print("Leyendo y mostrando los resultados...")
    if os.path.exists('final_output.txt'):
        reader = MatrixLoader('final_output.txt')
        reader.get_matrix()
    else:
        print("No se encontraron resultados de MapReduce.")

if __name__ == '__main__':
    main()
