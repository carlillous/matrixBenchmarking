class MatrixMapper:
    def __init__(self):
        self.dim = None  # Dimensión de las matrices A y B (ambas son cuadradas y del mismo tamaño)

    def map(self, _, line):
        # Si la línea contiene las dimensiones, actualiza la variable y no emite nada
        if line.startswith('dimensions'):
            _, self.dim = line.split(',')
            self.dim = int(self.dim)
            return

        # Parsear la línea para obtener la matriz y sus índices i, j y el valor
        matrix, i, j, value = line.split(',')
        i, j, value = int(i), int(j), float(value)

        # Emitir pares clave-valor para los elementos de la matriz
        # Asumiendo que tanto A como B son simétricas y de la misma dimensión
        if matrix in ('A', 'B'):
            for k in range(self.dim):
                # Emitir para la posición (i, k) de la matriz resultante
                yield (i, k), (matrix, j, value)
                # Dado que es simétrica, también emitimos para la posición (j, k) si i != j
                if i != j:
                    yield (j, k), (matrix, i, value)
