import random

class MatrixBuilder:
    def __init__(self, dimension, filename='matrix_input.txt', max_value=100.0):
        self.dimension = dimension
        self.filename = filename
        self.max_value = max_value

    def generate_matrices(self):
        with open(self.filename, 'w',encoding='utf-8') as f:
            for i in range(self.dimension):
                for j in range(i, self.dimension):
                    value = random.uniform(0, self.max_value)
                    f.write(f'A,{i},{j},{value:.2f}\n')
                    if i != j:
                        f.write(f'A,{j},{i},{value:.2f}\n')
            for i in range(self.dimension):
                for j in range(i, self.dimension):
                    value = random.uniform(0, self.max_value)
                    f.write(f'B,{i},{j},{value:.2f}\n')
                    if i != j:
                        f.write(f'B,{j},{i},{value:.2f}\n')

