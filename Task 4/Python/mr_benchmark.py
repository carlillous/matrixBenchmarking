import timeit
from mm_mr.mr_runner import run_mapreduce_job
from utils.matrix_builder import MatrixBuilder
from utils.out_treatment import combine_output_files, clean_output_directory

class MatrixMultiplicationMRBenchmark():

    def _wrapper(self,func, *args, **kwargs):
        def wrapped():
            return func(*args, **kwargs)
        return wrapped

    def _matrixmultiplication_mr_execution(self,n):

        input_file = 'matrix_input.txt'
        output_dir = 'output'

        builder = MatrixBuilder(n, filename=input_file)
        builder.generate_matrices()

        wrapped = self._wrapper(run_mapreduce_job, input_file, output_dir, n)
        execution_time = timeit.timeit(wrapped, number=10)

        combine_output_files(output_dir, 'final_output.txt')
        clean_output_directory(output_dir)

        return execution_time

    def matrixmultiplication_mr_benchmark(self):
        n = [8, 16, 32, 64, 128, 256, 512, 1024, 2048]
        times = []
        print("[INFO] Starting Benchmark...")
        for i in range(len(n)):
            exec_time = self._matrixmultiplication_mr_execution(n[i])
            print(f"i = {n[i]} -> {round(exec_time, 5)}")
            times.append(round(exec_time, 5))
        print("[INFO] Benchmark completed.")

        return times