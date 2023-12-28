import os
from utils.matrix_builder import MatrixBuilder
from utils.matrix_reader import MatrixReader
from mm_mr.matrix_multiplication_mr import MatrixMultiplicationMR

def run_mapreduce_job(input_file, output_dir):
    mr_job = MatrixMultiplicationMR(args=[input_file, '--output-dir', output_dir])
    with mr_job.make_runner() as runner:
        runner.run()

def main():
    # Configuración
    dimension = 10
    input_filename = 'matrix_input.txt'
    output_directory = 'output'
    output_filename = os.path.join(output_directory, 'part-00000')  # mrjob genera este archivo de salida por defecto

    # Paso 1: Generar matrices aleatorias y guardarlas en un archivo
    print("Generando matrices aleatorias...")
    builder = MatrixBuilder(dimension, filename=input_filename)
    builder.generate_matrices()

    # Paso 2: Ejecutar el trabajo MapReduce
    print("Ejecutando el trabajo MapReduce...")
    run_mapreduce_job(input_filename, output_directory)

    # Paso 3: Leer y mostrar el resultado del trabajo MapReduce
    print("Leyendo y mostrando los resultados...")
    if os.path.exists(output_filename):
        reader = MatrixReader(filename=output_filename)
        reader.read_matrix()
    else:
        print("No se encontraron resultados de MapReduce.")

if __name__ == '__main__':
    main()
