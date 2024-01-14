class MatrixReducer:
    def reduce(self, key, values):
        hash_A = {}
        hash_B = {}

        for value in values:
            if value[0] == 'A':
                _, j, a_ij = value
                hash_A[j] = a_ij
            elif value[0] == 'B':
                _, j, b_jk = value
                hash_B[j] = b_jk

        result = sum(hash_A[j] * hash_B.get(j, 0) for j in hash_A)

        yield key, result