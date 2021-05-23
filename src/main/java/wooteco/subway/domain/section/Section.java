package wooteco.subway.domain.section;

import wooteco.subway.domain.station.Station;

public class Section {
    private Long id;
    private Station upStation;
    private Station downStation;
    private Integer distance;

    public Section() {
    }

    public Section(Long id, Station upStation, Station downStation, Integer distance) {
        this.id = id;
        this.upStation = upStation;
        this.downStation = downStation;
        this.distance = distance;
    }

    public Section(Station upStation, Station downStation, Integer distance) {
        this.upStation = upStation;
        this.downStation = downStation;
        this.distance = distance;
    }

    public Long getId() {
        return id;
    }

    public Station getUpStation() {
        return upStation;
    }

    public Station getDownStation() {
        return downStation;
    }

    public Integer getDistance() {
        return distance;
    }
}