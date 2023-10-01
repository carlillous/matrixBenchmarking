class MatrixMultiplication:

    def execute(self,op):
        a, b = op
        assert len(a) == len(b)
        n = len(a)
        c = [[0 for _ in range(n)] for _ in range(n)]
        for i in range(n):
            for j in range(n):
                for k in range(n):
                    c[i][j] += a[i][k] * b[k][k]
        return c


