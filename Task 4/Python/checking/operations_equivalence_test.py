from utils.matrix_loader import MatrixLoader

class MatrixMultiplication:
    def __init__(self):
        self.matrix_a = {}
        self.matrix_b = {}
        self.matrix_c = {}

    def read_input_from_file(self, file_path):
        with open(file_path, 'r') as file:
            for line in file:
                parts = line.strip().split(',')
                if len(parts) != 4:
                    continue
                matrix_name, i, j, value = parts[0], int(parts[1]), int(parts[2]), float(parts[3])

                if matrix_name == 'A':
                    if i not in self.matrix_a:
                        self.matrix_a[i] = {}
                    self.matrix_a[i][j] = value
                elif matrix_name == 'B':
                    if j not in self.matrix_b:
                        self.matrix_b[j] = {}
                    self.matrix_b[j][i] = value

    def multiply_matrices(self):
        for i in self.matrix_a:
            for j in self.matrix_b:
                if i not in self.matrix_c:
                    self.matrix_c[i] = {}
                if j not in self.matrix_c[i]:
                    self.matrix_c[i][j] = 0
                for k in self.matrix_a[i]:
                    if k in self.matrix_b[j]:
                        self.matrix_c[i][j] += self.matrix_a[i][k] * self.matrix_b[j][k]

    def get_result(self):
        max_row = max(self.matrix_c.keys()) if self.matrix_c else 0
        max_col = max((max(cols.keys()) for cols in self.matrix_c.values()), default=0)
        result_matrix = [[0 for _ in range(max_col + 1)] for _ in range(max_row + 1)]

        for i in self.matrix_c:
            for j in self.matrix_c[i]:
                result_matrix[i][j] = self.matrix_c[i][j]

        return result_matrix


file_path = 'matrix_input.txt'

matrix_multiplication = MatrixMultiplication()
matrix_multiplication.read_input_from_file(file_path)
matrix_multiplication.multiply_matrices()
result_matrix = matrix_multiplication.get_result()


file_path2 = 'salida.txt'

matrix_loader = MatrixLoader(file_path2)
matrix_loader.load_matrix()
matrix_mr = matrix_loader.get_matrix()

print(matrix_mr == result_matrix)

