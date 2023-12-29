from mrjob.job import MRJob
from mrjob.step import MRStep
from mm_mr.matrix_mapper import MatrixMapper
from mm_mr.matrix_reducer import MatrixReducer
class MatrixMultiplicationMR(MRJob):

    def steps(self):
        return [
            MRStep(
                mapper=self.mapper,
                reducer=self.reducer
            )
        ]

    def mapper(self, _, line):
        # Assuming m and p are passed as job arguments or defined globally
        mapper = MatrixMapper(m=self.options.m_dimension, p=self.options.p_dimension)
        return mapper.map(_, line)

    def reducer(self, key, values):
        reducer = MatrixReducer()
        return reducer.reduce(key, values)

    def configure_args(self):
        super(MatrixMultiplicationMR, self).configure_args()
        self.add_passthru_arg('--m-dimension', type=int, help='Dimension m of the matrix')
        self.add_passthru_arg('--p-dimension', type=int, help='Dimension p of the matrix')

if __name__ == '__main__':
    MatrixMultiplicationMR.run()