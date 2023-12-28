class MatrixReducer:
    def reduce(self, key, values):
        # Crear estructuras para almacenar los valores de A y B
        values_A = {}
        values_B = {}

        # Separar los valores de A y B
        for matrix, pos, value in values:
            if matrix == 'A':
                values_A[pos] = value
            elif matrix == 'B':
                values_B[pos] = value

        # Calcular el producto para el elemento de la matriz C
        sum_product = sum(values_A[k] * values_B[k] for k in values_A if k in values_B)

        yield key, sum_product
