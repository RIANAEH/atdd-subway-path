package wooteco.subway.domain;

import java.util.List;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;

public class PathFinder {

    final private DijkstraShortestPath<Station, DefaultWeightedEdge> dijkstraShortestPath;

    public PathFinder(final List<Station> stations, final List<Section> sections) {
        final WeightedMultigraph<Station, DefaultWeightedEdge> graph = new WeightedMultigraph(
                DefaultWeightedEdge.class);
        for (final Station station : stations) {
            graph.addVertex(station);
        }
        for (final Section section : sections) {
            graph.setEdgeWeight(graph.addEdge(section.getUpStation(), section.getDownStation()), section.getDistance());
            graph.setEdgeWeight(graph.addEdge(section.getDownStation(), section.getUpStation()), section.getDistance());
        }

        this.dijkstraShortestPath = new DijkstraShortestPath(graph);
    }

    public Path findPath(final Station source, final Station target) {
        validateSameStation(source, target);
        final List<Station> route = dijkstraShortestPath.getPath(source, target).getVertexList();
        final int distance = (int) dijkstraShortestPath.getPath(source, target).getWeight();
        return new Path(route, distance);
    }

    private void validateSameStation(final Station source, final Station target) {
        if (source.equals(target)) {
            throw new IllegalArgumentException("출발역과 도착역은 같을 수 없습니다.");
        }
    }
}
