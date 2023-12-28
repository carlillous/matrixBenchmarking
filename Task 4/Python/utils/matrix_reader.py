class MatrixReader:
    def __init__(self, filename='matrix_output.txt'):
        self.filename = filename

    def read_matrix(self):
        matrix = {}
        with open(self.filename, 'r') as f:
            for line in f:
                line = line.strip()
                key, value = line.split('\t')
                i, j = eval(key)  # Convierte la cadena "(i, j)" en una tupla (i, j)
                matrix[(i, j)] = float(value)

        # Si se desea imprimir la matriz
        for i in range(max(i for i, j in matrix.keys()) + 1):
            for j in range(max(j for i, j in matrix.keys()) + 1):
                print(f'{matrix.get((i, j), 0):.2f}', end=' ')
            print()
