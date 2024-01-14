class MatrixMapper:

    def __init__(self, m, p):
        self.m = m
        self.p = p

    def map(self, _, line):
        elements = line.split(',')
        matrix_name = elements[0]  # "A" or "B"
        i = int(elements[1])
        j = int(elements[2])
        value = float(elements[3])

        if matrix_name == "A":
            for k in range(self.p):
                yield (i, k), ('A', j, value)
        else:  # matrix_name == "B"
            for k in range(self.m):
                yield (k, j), ('B', i, value)

