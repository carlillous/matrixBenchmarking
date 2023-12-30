from mm_mr.matrix_multiplication_mr import MatrixMultiplicationMR

def run_mapreduce_job(input_file, output_dir, dim):
    mr_job = MatrixMultiplicationMR(
        args=[input_file, '--output-dir', output_dir, '--m-dimension', str(dim), '--p-dimension', str(dim)])
    with mr_job.make_runner() as runner:
        runner.run()