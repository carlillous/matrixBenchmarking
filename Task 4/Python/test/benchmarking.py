from mr_benchmark import MatrixMultiplicationMRBenchmark
import logging

logging.basicConfig(level=logging.CRITICAL)

mrbenchmark = MatrixMultiplicationMRBenchmark()
exec = mrbenchmark.matrixmultiplication_mr_benchmark()
print(exec)