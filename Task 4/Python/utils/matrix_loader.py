class MatrixLoader:
    def __init__(self, file_path):
        self.file_path = file_path
        self.matrix = {}

    def load_matrix(self):
        with open(self.file_path, 'r', encoding='utf-16') as file:  # Ajusta la codificación si es necesario
            for line in file:
                line = line.strip()
                if not line:
                    continue  # Ignorar líneas vacías
                position, value = line.split('\t')
                i, j = self._parse_position(position)
                self._set_value(i, j, float(value))

    def _parse_position(self, position):
        position = position.strip("[]")
        i, j = map(int, position.split(', '))
        return i, j

    def _set_value(self, i, j, value):
        if i not in self.matrix:
            self.matrix[i] = {}
        self.matrix[i][j] = value

    def get_matrix(self):
        # Convert the dictionary to a 2D list
        if not self.matrix:
            return []

        max_row = max(self.matrix.keys())
        max_col = max(max(cols.keys()) for cols in self.matrix.values())
        result_matrix = [[0 for _ in range(max_col + 1)] for _ in range(max_row + 1)]

        for i in self.matrix:
            for j in self.matrix[i]:
                result_matrix[i][j] = self.matrix[i][j]

        return result_matrix
