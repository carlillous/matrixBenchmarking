from mrjob.job import MRJob
from mrjob.step import MRStep
from mm_mr.matrix_mapper import MatrixMapper
from mm_mr.matrix_reducer import MatrixReducer

class MatrixMultiplicationMR(MRJob):

    def steps(self):
        return [
            MRStep(mapper=MatrixMapper.map,
                   reducer=MatrixReducer.reduce)
        ]

if __name__ == '__main__':
    MatrixMultiplicationMR.run()
