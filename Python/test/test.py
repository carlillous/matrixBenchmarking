from mmultiplication_benchmarking import MatrixMultiplicationBenchmarking
import pytest

@pytest.mark.benchmark
def test_matrix_multiplication(benchmark):
    benchmark.pedantic(MatrixMultiplicationBenchmarking().multiplication,
                        args = (MatrixMultiplicationBenchmarking.Operands(),)
                       ,rounds=2,warmup_rounds=1,iterations=3)