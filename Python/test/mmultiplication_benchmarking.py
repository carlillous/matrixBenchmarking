import random
from src.matrix_multiplication import MatrixMultiplication

class MatrixMultiplicationBenchmarking:

    class Operands:
        n = 2048
        def setup(self):
            a = [[random.random() for _ in range(self.n)] for _ in range(self.n)]
            b = [[random.random() for _ in range(self.n)] for _ in range(self.n)]
            return a,b

    def multiplication(self,Operands):
        return MatrixMultiplication().execute(Operands.setup())


